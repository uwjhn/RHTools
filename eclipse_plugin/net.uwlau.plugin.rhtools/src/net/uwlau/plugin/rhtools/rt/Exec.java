package net.uwlau.plugin.rhtools.rt;

import com.jcraft.jsch.*;
import java.io.*;

public class Exec {
	
	public static void ssh_exec()
	{
	

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

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(net.uwlau.plugin.rhtools.handlers.ConfigHandler.command);

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
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			System.out.println(e);

		}
	}
}

