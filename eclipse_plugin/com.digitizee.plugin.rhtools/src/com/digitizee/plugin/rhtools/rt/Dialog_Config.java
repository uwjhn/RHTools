package com.digitizee.plugin.rhtools.rt;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.SelectionEvent;

public class Dialog_Config extends TitleAreaDialog {

	private Text txt_host;
	private Text txt_port;
	private Text txt_user;
	private Text txt_passwd;
	private Text txt_binary_path;

	private String host;
	private String port;
	private String user;
	private String passwd;
	private String binary_path;

	private boolean bool_flag_kill;
	private boolean bool_flag_SCP;
	private boolean bool_flag_Chmod;
	private boolean bool_flag_Exec;

	private Button KillFlagbox;
	private Button SCPFlagbox;
	private Button ChmodFlagbox;
	private Button ExecFlagbox;

	public Dialog_Config(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("RHTools");
		setMessage("Configurations", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createHost(container);
		createPort(container);
		createUser(container);
		createPasswd(container);
		createBinaryData(container);
		createBinaryText(container);
		createKillFlagbox(container);
		createSCPFlagbox(container);
		createChmodFlagbox(container);
		createExecFlagbox(container);

		return area;
	}

	private void createHost(Composite container) {
		Label lbthost = new Label(container, SWT.NONE);
		lbthost.setText("Host:");

		GridData datahost = new GridData();
		datahost.grabExcessHorizontalSpace = true;
		datahost.horizontalAlignment = GridData.FILL;

		txt_host = new Text(container, SWT.BORDER);
		txt_host.setLayoutData(datahost);
		txt_host.setText(com.digitizee.plugin.rhtools.handlers.ConfigHandler.host);
	}

	private void createPort(Composite container) {
		Label lbtport = new Label(container, SWT.NONE);
		lbtport.setText("Port:");

		GridData dataport = new GridData();
		dataport.grabExcessHorizontalSpace = true;
		dataport.horizontalAlignment = GridData.FILL;

		txt_port = new Text(container, SWT.BORDER);
		txt_port.setLayoutData(dataport);
		txt_port.setText(Integer.toString(com.digitizee.plugin.rhtools.handlers.ConfigHandler.port));
	}

	private void createUser(Composite container) {
		Label lbtuser = new Label(container, SWT.NONE);
		lbtuser.setText("User:");

		GridData datauser = new GridData();
		datauser.grabExcessHorizontalSpace = true;
		datauser.horizontalAlignment = GridData.FILL;

		txt_user = new Text(container, SWT.BORDER);
		txt_user.setLayoutData(datauser);
		txt_user.setText(com.digitizee.plugin.rhtools.handlers.ConfigHandler.user);
	}

	private void createPasswd(Composite container) {
		Label lbtpasswd = new Label(container, SWT.NONE);
		lbtpasswd.setText("Password:");

		GridData datapasswd = new GridData();
		datapasswd.grabExcessHorizontalSpace = true;
		datapasswd.horizontalAlignment = GridData.FILL;

		txt_passwd = new Text(container, SWT.BORDER);
		txt_passwd.setLayoutData(datapasswd);
		txt_passwd.setText(com.digitizee.plugin.rhtools.handlers.ConfigHandler.passwd);
	}

	private void createBinaryText(Composite container) {
		Label lbtpath = new Label(container, SWT.NONE);
		lbtpath.setText("Selected binary:");

		GridData datapath = new GridData();
		datapath.grabExcessHorizontalSpace = true;
		datapath.horizontalAlignment = GridData.FILL;

		txt_binary_path = new Text(container, SWT.BORDER);
		txt_binary_path.setLayoutData(datapath);
		txt_binary_path.setText(com.digitizee.plugin.rhtools.handlers.ConfigHandler.binary_path);
	}

	private void createBinaryData(Composite container) {
		Label lbtbinary = new Label(container, SWT.NONE);
		lbtbinary.setText("Select binary:");

		GridData databinarydata = new GridData(SWT.FILL, SWT.BEGINNING, true, false);

		Button button = new Button(container, SWT.NONE);
		button.setLayoutData(databinarydata);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {

				Button mButton = new Button(container, SWT.NONE);

				FileDialog dlg = new FileDialog(mButton.getShell(), SWT.OPEN);
				dlg.setText("Open");
				binary_path = dlg.open();
				if (binary_path == null)
					return;
				txt_binary_path.setText(binary_path);
			}
		});
	}

	private void createKillFlagbox(Composite container) {
		Label lbtpath = new Label(container, SWT.NONE);
		lbtpath.setText("Kill old binary activities while running RHTools:");

		GridData datapath = new GridData();
		datapath.grabExcessHorizontalSpace = true;
		datapath.horizontalAlignment = GridData.FILL;

		KillFlagbox = new Button(container, SWT.CHECK);

		if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_kill) {
			KillFlagbox.setSelection(true);
		} else {
			KillFlagbox.setSelection(false);
		}
	}
	
	private void createSCPFlagbox(Composite container) {
		Label lbtpath = new Label(container, SWT.NONE);
		lbtpath.setText("Copy new binary while running RHTools:");

		GridData datapath = new GridData();
		datapath.grabExcessHorizontalSpace = true;
		datapath.horizontalAlignment = GridData.FILL;

		SCPFlagbox = new Button(container, SWT.CHECK);

		if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_scp) {
			SCPFlagbox.setSelection(true);
		} else {
			SCPFlagbox.setSelection(false);
		}
	}
	
	private void createChmodFlagbox(Composite container) {
		Label lbtpath = new Label(container, SWT.NONE);
		lbtpath.setText("Make binary executable while running RHTools:");

		GridData datapath = new GridData();
		datapath.grabExcessHorizontalSpace = true;
		datapath.horizontalAlignment = GridData.FILL;

		ChmodFlagbox = new Button(container, SWT.CHECK);

		if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_chmod) {
			ChmodFlagbox.setSelection(true);
		} else {
			ChmodFlagbox.setSelection(false);
		}
	}
	
	private void createExecFlagbox(Composite container) {
		Label lbtpath = new Label(container, SWT.NONE);
		lbtpath.setText("Execute binary while running RHTools:");

		GridData datapath = new GridData();
		datapath.grabExcessHorizontalSpace = true;
		datapath.horizontalAlignment = GridData.FILL;

		ExecFlagbox = new Button(container, SWT.CHECK);

		if (com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_exec) {
			ExecFlagbox.setSelection(true);
		} else {
			ExecFlagbox.setSelection(false);
		}
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		host = txt_host.getText();
		port = txt_port.getText();
		user = txt_user.getText();
		passwd = txt_passwd.getText();
		binary_path = txt_binary_path.getText();

		// save flags
		if (KillFlagbox.getSelection() == true) {
			bool_flag_kill = true;
		} else {
			bool_flag_kill = false;
		}
		if (SCPFlagbox.getSelection() == true) {
			bool_flag_SCP = true;
		} else {
			bool_flag_SCP = false;
		}
		if (ChmodFlagbox.getSelection() == true) {
			bool_flag_Chmod = true;
		} else {
			bool_flag_Chmod = false;
		}
		if (ExecFlagbox.getSelection() == true) {
			bool_flag_Exec = true;
		} else {
			bool_flag_Exec = false;
		}

	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return passwd;
	}

	public String getBinaryPath() {
		return binary_path;
	}

	public Boolean getKillFlag() {
		return bool_flag_kill;
	}
	
	public Boolean getSCPFlag() {
		return bool_flag_SCP;
	}

	public Boolean getChmodFlag() {
		return bool_flag_Chmod;
	}
	
	public Boolean getExecFlag() {
		return bool_flag_Exec;
	}
}