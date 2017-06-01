package net.uwlau.plugin.rhtools.rt;

import com.jcraft.jsch.*;
import java.io.*;
import org.eclipse.ui.console.*;

public class Exec {

	public static void rt_run() {

		try {
			JSch jsch = new JSch();

			Session session = jsch.getSession(net.uwlau.plugin.rhtools.handlers.ConfigHandler.user,
					net.uwlau.plugin.rhtools.handlers.ConfigHandler.host,
					net.uwlau.plugin.rhtools.handlers.ConfigHandler.port);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(net.uwlau.plugin.rhtools.handlers.ConfigHandler.passwd);
			session.connect();

			// console out in target ide
			MessageConsole myConsole = net.uwlau.plugin.rhtools.rt.Console_out.findConsole("RHTools*Console");
			MessageConsoleStream out = myConsole.newMessageStream();
			out.println("*RHTOOLS -> Starting SSH/SCP Tasks");
			
			if (net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_kill) {
				out.println("*RHTOOLS -> Kill binary activities");
				ssh_exec(session, "kill -9 $(pidof " + net.uwlau.plugin.rhtools.handlers.ConfigHandler.binary_name + ")");
			}
			if (net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_scp) {
				out.println("*RHTOOLS -> Copy binary to remote hardware");
				ssh_exec(session, "echo scp todo");
			} 
			if (net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_chmod) {
				out.println("*RHTOOLS -> Make binary executeable");
				ssh_exec(session,  "chmod +x " + net.uwlau.plugin.rhtools.handlers.ConfigHandler.binary_name);
			}
			if (net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_exec) {
				out.println("*RHTOOLS -> Run binary");
				ssh_exec(session, "./" + net.uwlau.plugin.rhtools.handlers.ConfigHandler.binary_name);
			}

			out.println("*RHTOOLS -> SSH/SCP Tasks done.");
			session.disconnect();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	public static void ssh_exec(Session session, String cmd) {

		try {
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(cmd);

			// channel.setInputStream(System.in);
			channel.setInputStream(null);

			// channel.setOutputStream(System.out);

			// FileOutputStream fos=new FileOutputStream("/tmp/stderr");
			// ((ChannelExec)channel).setErrStream(fos);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("*RHTOOLS --> SSH/SCP COMMAND DONE");
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();

		}

		catch (Exception e) {
			System.out.println(e);

		}

	}
	
}
