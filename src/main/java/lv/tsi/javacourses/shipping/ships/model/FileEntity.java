package lv.tsi.javacourses.shipping.ships.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity(name = "ImageFile")
@Table(name = "imagefiles")
public class FileEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_name")
    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] cover) {
        this.data = cover;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return Objects.equals(id, that.id) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(contentType, that.contentType) &&
                Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, contentType, fileName);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", data=" + Arrays.toString(data) +
                ", contentType='" + contentType + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
