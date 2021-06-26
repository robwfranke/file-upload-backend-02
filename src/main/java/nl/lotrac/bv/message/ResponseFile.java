package nl.lotrac.bv.message;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString

public class ResponseFile {

    private String id;
    private String name;
    private String url;
    private String type;
    private long size;

    public ResponseFile(String id, String name, String url, String type, long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;

    }
}
