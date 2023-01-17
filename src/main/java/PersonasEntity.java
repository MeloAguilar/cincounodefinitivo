
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="Personas")
public class PersonasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private int idPersona;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "saldo")
    private double saldo;

    //Para las entidades de Hibernate el constructor debe ser público por huevos.
    public PersonasEntity() {
    }

    public PersonasEntity(int idPersona, String nombre, double saldo) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public PersonasEntity(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    //Aquí pongo los Getter y Setter.
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //Sobreescribo el toString a mi gusto.
    @Override
    public String toString() {
        return String.format("ID: %d.  Nombre: %s.  Saldo: %.2f", idPersona, nombre, saldo);
    }

}