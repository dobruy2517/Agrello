package helpers;

public class ListData {


    private String clientEmail;


    private String companyEmail;
    private String clientPassword;

    private String clientEmailName = "client email";


    private String companyEmailName = "company email";
    private String clientPasswordName = "client password";

    public String getCompanyEmailName() {
        return companyEmailName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getClientEmailName() {
        return clientEmailName;
    }


    public String getClientPasswordName() {
        return clientPasswordName;
    }


    public String getClientEmail() {
        return clientEmail;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
