package net.uwlau.plugin.rhtools.rt;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

public class Console_out {
	
	public static MessageConsole findConsole(String name){
		  ConsolePlugin plugin = ConsolePlugin.getDefault();
		  IConsoleManager conMan = plugin.getConsoleManager();
		  IConsole[] existing = conMan.getConsoles();
		  for (int i = 0; i<existing.length; i++){
		    if (name.equals(existing[i].getName())){
		      return (MessageConsole)existing[i];
		    }
		  }
		  //no console found -> create new one
		  MessageConsole newConsole = new MessageConsole(name, null);
		  conMan.addConsoles(new IConsole[]{newConsole});
		  return newConsole;
		}
	

}
