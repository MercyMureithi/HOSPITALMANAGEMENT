package hospital.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String specialty;
    
    private String license;
    private Integer experience;
    
    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;

    // Constructors
    public Doctor() {}
    
    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    
    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }
    
    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }
    
    public Set<Appointment> getAppointments() { return appointments; }
    public void setAppointments(Set<Appointment> appointments) { this.appointments = appointments; }
}
