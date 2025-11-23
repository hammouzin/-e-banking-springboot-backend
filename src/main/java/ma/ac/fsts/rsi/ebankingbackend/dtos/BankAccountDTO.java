package ma.ac.fsts.rsi.ebankingbackend.dtos;

public class BankAccountDTO {
    private String type ;


    public BankAccountDTO() {
    }

    public BankAccountDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
