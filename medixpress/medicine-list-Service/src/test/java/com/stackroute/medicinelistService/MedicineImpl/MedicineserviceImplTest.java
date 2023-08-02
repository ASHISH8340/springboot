package com.stackroute.medicinelistService.MedicineImpl;

import com.stackroute.medicinelistService.model.Medicine;
import com.stackroute.medicinelistService.repository.MedicineRepository;
import com.stackroute.medicinelistService.service.MedicineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

//@SpringBootTest
//public class MedicineserviceImplTest {
//
//    @Autowired
//    MedicineService medicineService;
//
//    @MockBean
//    private MedicineRepository repo;


//    @Test
//    public void addMedicine() {
//        assertThrows(NullPointerException.class, () -> {
//            Medicine medicine = new Medicine();
//            medicine.setMedicineId("33");
//            medicine.getShopEmail("AAA@gmail");
//            medicine.setImage(null);
//            when(repo.save(medicine)).thenReturn(medicine);
//            Medicine medicineresult = (Medicine) MedicineService.addMedicine(medicine, null);
//            assertEquals(medicine, medicineresult);
//
//        });
//    }
//    @Test
//    public void testGetmedicineId(){
//        Medicine medicine=new Medicine();
//        medicine.setMedicineId("32");
//        when(repo.findBymedicineId(medicine.getMedicineId())).thenReturn(medicine);
//        Medicine medicineresult= (Medicine) medicineService.getMedicineById(medicine.getMedicineId());
//        assertEquals(medicine,medicineresult);
//    }
//
//}