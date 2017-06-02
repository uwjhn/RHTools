package net.uwlau.plugin.rhtools.rt;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Dialog_Config extends TitleAreaDialog {

    private Text txt_host;
    private Text txt_port;
    private Text txt_user;
    private Text txt_passwd;
    private Text txt_binary_name;
    private Text txt_path_to_binary;

    private String host;
    private String port;
    private String user;
    private String passwd;
    private String binary_name;
    private String path_to_binary;
    
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
        createBinaryName(container);
        createPathToBinary(container);
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
    }
    
    private void createPort(Composite container) {
        Label lbtport = new Label(container, SWT.NONE);
        lbtport.setText("Port:");

        GridData dataport = new GridData();
        dataport.grabExcessHorizontalSpace = true;
        dataport.horizontalAlignment = GridData.FILL;

        txt_port = new Text(container, SWT.BORDER);
        txt_port.setLayoutData(dataport);
    }

    private void createUser(Composite container) {
        Label lbtuser = new Label(container, SWT.NONE);
        lbtuser.setText("User:");

        GridData datauser = new GridData();
        datauser.grabExcessHorizontalSpace = true;
        datauser.horizontalAlignment = GridData.FILL;

        txt_user = new Text(container, SWT.BORDER);
        txt_user.setLayoutData(datauser);
    }
    
    private void createPasswd(Composite container) {
        Label lbtpasswd = new Label(container, SWT.NONE);
        lbtpasswd.setText("Password:");

        GridData datapasswd = new GridData();
        datapasswd.grabExcessHorizontalSpace = true;
        datapasswd.horizontalAlignment = GridData.FILL;

        txt_passwd = new Text(container, SWT.BORDER);
        txt_passwd.setLayoutData(datapasswd);
    }
    
    private void createBinaryName(Composite container) {
        Label lbtbinary = new Label(container, SWT.NONE);
        lbtbinary.setText("Binary name:");

        GridData databinary = new GridData();
        databinary.grabExcessHorizontalSpace = true;
        databinary.horizontalAlignment = GridData.FILL;

        txt_binary_name = new Text(container, SWT.BORDER);
        txt_binary_name.setLayoutData(databinary);
    }
    
    private void createPathToBinary(Composite container) {
        Label lbtpath = new Label(container, SWT.NONE);
        lbtpath.setText("Path to binary:");

        GridData datapath = new GridData();
        datapath.grabExcessHorizontalSpace = true;
        datapath.horizontalAlignment = GridData.FILL;

        txt_path_to_binary = new Text(container, SWT.BORDER);
        txt_path_to_binary.setLayoutData(datapath);
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
    	binary_name = txt_binary_name.getText();
    	path_to_binary = txt_path_to_binary.getText();
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
    
    public String getBinaryName() {
        return binary_name;
    }
    
    public String getPathToBinary() {
        return path_to_binary;
    }

}