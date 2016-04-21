//
//
//
//
public enum Commands
{
    PUT("put"), GET("get"), DUMP("dump"), STATE("state"), QUIT("quit");

    private String value;

    Commands(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
