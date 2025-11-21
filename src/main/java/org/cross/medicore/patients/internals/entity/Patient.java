package org.cross.medicore.patients.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.cross.medicore.shared.BloodGroup;
import org.cross.medicore.shared.Sex;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    @Getter
    private long patientId;

    @Column(name = "first_name", nullable = false, length = 50)
    @Getter @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Getter @Setter
    private String lastName;

    @Column(name = "middle_name", length = 50)
    @Getter @Setter
    private String middleName;

    @Column(name = "email", length = 150)
    @Getter @Setter
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    @Getter @Setter
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    @Getter @Setter
    private LocalDate birthDate;

    @Column(name = "height")
    @Getter @Setter
    private int height;

    @Column(name = "weight")
    @Getter @Setter
    private int weight;

    @Column(name = "aadhaar")
    @Getter @Setter
    private String aadhaar;

    @Column(name = "blood_group")
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name = "sex")
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true)
    @Getter @Setter
    private PatientAddress address;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Getter
    private PatientStatus status;

    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    @Setter @Getter
    private long userId;

    public int getAgeYears(){
        if(birthDate == null){
            throw new IllegalStateException("birth date is not assigned");
        }

        LocalDate today = LocalDate.now();

        if(birthDate.isAfter(today)){
            throw new IllegalArgumentException("birth date can't be after today");
        }

        return Period.between(birthDate, today).getYears();
    }

    @Transient
    private static final Map<String, Field> FIELD_CACHE = new HashMap<>();

    /**
     * Retrieves the value of a field by its name using reflection.
     * This method provides dynamic field access without compile-time knowledge
     * of the field name. Fields are cached for improved performance on repeated access.
     *
     * @param fieldName the name of the field to retrieve the value from
     * @return the value of the specified field, or null if the field value is null
     * @throws RuntimeException if the field does not exist or cannot be accessed
     * @throws IllegalStateException if the field cannot be made accessible
     */
    public Object getField(String fieldName){
        try{
            Field f = FIELD_CACHE.computeIfAbsent(fieldName, (name) -> {
                try {
                    Field field = this.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    return field;
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            });

            return f.get(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
