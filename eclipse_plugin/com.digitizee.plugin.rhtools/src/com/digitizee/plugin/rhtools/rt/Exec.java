package com.digitizee.plugin.rhtools.rt;

import com.jcraft.jsch.*;
import java.io.*;
import org.eclipse.ui.console.*;
import org.eclipse.ui.PlatformUI;

public class Exec {

	public static boolean output = true;

	// get binary name without path
	static int lastSlash = (com.digitizee.plugin.rhtools.handlers.ConfigHandler.binary_path.lastIndexOf("/") + 1);
	static int StringLength = com.digitizee.plugin.rhtools.handlers.ConfigHandler.binary_path.length();
	public static String binary_name = com.digitizee.plugin.rhtools.handlers.ConfigHandler.binary_path
			.substring(lastSlash, StringLength);

	public static <IProgressMonitor> void rt_run() {
		
		try {
			
			MessageConsole myConsole = com.digitizee.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();
			out.println("\n*RHTOOLS -> Starting RHTool Task(s)");

			JSch jsch = new JSch();

			Session session = jsch.getSession(com.digitizee.plugin.rhtools.handlers.ConfigHandler.user,
					com.digitizee.plugin.rhtools.handlers.ConfigHandler.host,
					com.digitizee.plugin.rhtools.handlers.ConfigHandler.port);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(com.digitizee.plugin.rhtools.handlers.ConfigHandler.passwd);
			session.connect();

			if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_kill) {
				out.println("*RHTOOLS --> Kill binary activities");
				ssh_exec(session, "kill -9 $(pidof " + binary_name + ")");
			}
			if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_scp) {
				out.println("*RHTOOLS --> Save & Build Project");
				// save project files
				PlatformUI.getWorkbench().saveAllEditors(false);
				// build project

				out.println("*RHTOOLS --> Copy binary to remote hardware");
				scp(session);
			}
			if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_chmod) {
				out.println("*RHTOOLS --> Make binary executeable");
				ssh_exec(session, "chmod +x " + binary_name);
			}
			if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_exec) {
				out.println("*RHTOOLS --> Run binary");
				output = false; // avoids hanging the IDE
				ssh_exec(session, "nohup ./" + binary_name);
				output = true;
			}
			if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_shutdown) {
				out.println("*RHTOOLS --> Shutdown device");
				ssh_exec(session, "sudo shutdown -h now");
			}
			if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_reboot) {
				out.println("*RHTOOLS --> Reboot device");
				ssh_exec(session, "sudo reboot");
			}
			if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_custom) {
				out.println("*RHTOOLS --> Custom command: " + com.digitizee.plugin.rhtools.handlers.ConfigHandler.custom_cmd);
				ssh_exec(session, com.digitizee.plugin.rhtools.handlers.ConfigHandler.custom_cmd);
			}

			out.println("*RHTOOLS -> RHTool Task(s) done.");
			session.disconnect();

		} catch (Exception e) {
			MessageConsole myConsole = com.digitizee.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();
			out.println("*RHTOOLS ERROR: " + e.toString());
		}

	}

	public static void ssh_exec(Session session, String cmd) {

		try {
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(cmd);

			// channel.setInputStream(System.in);
			channel.setInputStream(null);

			// FileOutputStream fos=new FileOutputStream("/tmp/stderr");
			// ((ChannelExec)channel).setErrStream(fos);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			MessageConsole myConsole = com.digitizee.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();

			if (output) {

				byte[] tmp = new byte[1024];
				while (true) {
					while (in.available() > 0) {
						int i = in.read(tmp, 0, 1024);
						if (i < 0)
							break;
						out.println(new String(tmp, 0, i));
					}
					if (channel.isClosed()) {
						if (in.available() > 0)
							continue;
						if (channel.getExitStatus() == 0) {
							out.println("*RHTOOLS ---> SSH Command finished");
						} else {

							out.println("*RHTOOLS SSH-ERROR: exit-status " + channel.getExitStatus());
						}
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (Exception ee) {
					}
				}
			}
			channel.disconnect();

		}

		catch (Exception e) {
			MessageConsole myConsole = com.digitizee.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();
			out.println(e.toString());
		}

	}

	public static void scp(Session session) {

		FileInputStream fis = null;
		try {

			// exec 'scp -t rfile' remotely
			String command = "scp " + " -t " + binary_name;
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream outStream = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			if (checkAck(in) != 0) {
				System.exit(0);
			}

			String lfile = com.digitizee.plugin.rhtools.handlers.ConfigHandler.binary_path;
			File _lfile = new File(lfile);

			// send "C0644 filesize filename", where filename should not include
			// '/'
			long filesize = _lfile.length();
			command = "C0644 " + filesize + " ";
			if (lfile.lastIndexOf('/') > 0) {
				command += lfile.substring(lfile.lastIndexOf('/') + 1);
			} else {
				command += lfile;
			}
			command += "\n";
			outStream.write(command.getBytes());
			outStream.flush();
			if (checkAck(in) != 0) {
				System.exit(0);
			}

			// send a content of lfile
			fis = new FileInputStream(lfile);
			byte[] buf = new byte[1024];
			while (true) {
				int len = fis.read(buf, 0, buf.length);
				if (len <= 0)
					break;
				outStream.write(buf, 0, len); // out.flush();
			}
			fis.close();
			fis = null;
			// send '\0'
			buf[0] = 0;
			outStream.write(buf, 0, 1);
			outStream.flush();
			if (checkAck(in) != 0) {
				System.exit(0);
			}
			outStream.close();

			MessageConsole myConsole = com.digitizee.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();
			out.println("*RHTOOLS ---> SCP Command finished");

			channel.disconnect();

		} catch (Exception e) {
			MessageConsole myConsole = com.digitizee.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();
			out.println("*RHTOOLS SCP-ERROR: " + e.toString());

			try {
				if (fis != null)
					fis.close();
			} catch (Exception ee) {
			}
		}
	}

	static int checkAck(InputStream in) throws IOException {
		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');

			MessageConsole myConsole = com.digitizee.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();

			if (b == 1) { // error
				out.println("*RHTOOLS SCP-ERROR: " + sb.toString());
			}
			if (b == 2) { // fatal error
				out.println("*RHTOOLS SCP-ERROR: " + sb.toString());
			}
		}
		return b;
	}

}
