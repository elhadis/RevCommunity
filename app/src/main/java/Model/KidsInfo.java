package Model;

public class KidsInfo {
    String kidName,kidFamily,familyNum,dat;

    public KidsInfo() {
    }

    public KidsInfo(String kidName, String kidFamily, String familyNum, String dat) {
        this.kidName = kidName;
        this.kidFamily = kidFamily;
        this.familyNum = familyNum;
        this.dat = dat;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getKidName() {
        return kidName;
    }

    public void setKidName(String kidName) {
        this.kidName = kidName;
    }

    public String getKidFamily() {
        return kidFamily;
    }

    public void setKidFamily(String kidFamily) {
        this.kidFamily = kidFamily;
    }

    public String getFamilyNum() {
        return familyNum;
    }

    public void setFamilyNum(String familyNum) {
        this.familyNum = familyNum;
    }
}
