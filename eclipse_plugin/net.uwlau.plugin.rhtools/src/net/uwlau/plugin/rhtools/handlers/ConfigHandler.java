package net.uwlau.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class ConfigHandler extends AbstractHandler {
	
	
	public static String host = "192.168.1.121";
	public static int port = 22;
	public static String user = "pi";
	public static String passwd = "raspberry";
	public static String binary_name = "rPI_Operator";
	public static String path_to_binary = "../../";
	
	public static boolean flag_kill = true;
	public static boolean flag_scp = true;
	public static boolean flag_chmod = true;
	public static boolean flag_exec = false;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"RHTools",
				"RHTools -> ** Config");
		return null;
	}
}
