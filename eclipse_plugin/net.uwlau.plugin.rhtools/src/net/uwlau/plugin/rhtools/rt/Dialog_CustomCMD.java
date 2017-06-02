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

public class Dialog_CustomCMD extends TitleAreaDialog {

    private Text txt_cmd;

    private String cmd;
    
    public Dialog_CustomCMD(Shell parentShell) {
        super(parentShell);
    }

    @Override
    public void create() {
        super.create();
        setTitle("RHTools");
        setMessage("Exec custom command", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        createCMD(container);
        return area;
    }

    private void createCMD(Composite container) {
        Label lbtCMD = new Label(container, SWT.NONE);
        lbtCMD.setText("Custom Command:");

        GridData dataCMD = new GridData();
        dataCMD.grabExcessHorizontalSpace = true;
        dataCMD.horizontalAlignment = GridData.FILL;

        txt_cmd = new Text(container, SWT.BORDER);
        txt_cmd.setLayoutData(dataCMD);
    }


    @Override
    protected boolean isResizable() {
        return true;
    }

    // save content of the Text fields because they get disposed
    // as soon as the Dialog closes
    private void saveInput() {
    	cmd = txt_cmd.getText();
    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

    public String getCMD() {
        return cmd;
    }

}