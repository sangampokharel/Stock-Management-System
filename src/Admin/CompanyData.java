package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompanyData {
    private final SimpleStringProperty iD;
    private final SimpleStringProperty shareamount;
    private final SimpleStringProperty sharerate;
    private final SimpleStringProperty dob;

    public CompanyData(String iD,String shareamount, String sharerate, String dob) {
        this.iD=new SimpleStringProperty(iD);
        this.shareamount = new SimpleStringProperty(shareamount);
        this.sharerate = new SimpleStringProperty(sharerate);
        this.dob = new SimpleStringProperty(dob);
    }



    public String getiD() {
        return iD.get();
    }

    public StringProperty iDProperty() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD.set(iD);
    }

    public String getShareamount() {
        return shareamount.get();

    }

    public StringProperty shareamountProperty() {
        return shareamount;
    }

    public void setShareamount(String shareamount) {
        this.shareamount.set(shareamount);
    }

    public String getSharerate() {
        return sharerate.get();
    }

    public StringProperty sharerateProperty() {
        return sharerate;
    }

    public void setSharerate(String sharerate) {
        this.sharerate.set(sharerate);
    }

    public String getDob() {
        return dob.get();
    }

    public StringProperty dobProperty() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }
}
