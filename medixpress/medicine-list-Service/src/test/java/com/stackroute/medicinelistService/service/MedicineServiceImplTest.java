//package com.stackroute.medicinelistService.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.anyDouble;
//import static org.mockito.Mockito.anyInt;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.stackroute.medicinelistService.model.Medicine;
//import com.stackroute.medicinelistService.repository.MedicineRepository;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//import org.bson.types.Binary;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Sort;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.multipart.MultipartFile;
//
//@ContextConfiguration(classes = {MedicineServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//class MedicineServiceImplTest {
//    @MockBean
//    private MedicineRepository medicineRepository;
//
//    @Autowired
//    private MedicineServiceImpl medicineServiceImpl;
//
//    @MockBean
//    private SequenceGeneartor sequenceGeneartor;
//
//    /**
//     * Method under test: {@link MedicineServiceImpl#getAllMedicines()}
//     */
//    @Test
//    void testGetAllMedicines() {
//        ArrayList<Medicine> medicineList = new ArrayList<>();
//        when(medicineRepository.findAll()).thenReturn(medicineList);
//        List<Medicine> actualAllMedicines = medicineServiceImpl.getAllMedicines();
//        assertSame(medicineList, actualAllMedicines);
//        assertTrue(actualAllMedicines.isEmpty());
//        verify(medicineRepository).findAll();
//    }
//
//
//    @Test
//    void testGetMedicineById() throws UnsupportedEncodingException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        medicine.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//        Optional<Medicine> ofResult = Optional.of(medicine);
//        when(medicineRepository.findById((String) any())).thenReturn(ofResult);
//        Optional<Medicine> actualMedicineById = medicineServiceImpl.getMedicineById("42");
//        assertSame(ofResult, actualMedicineById);
//        assertTrue(actualMedicineById.isPresent());
//        verify(medicineRepository).findById((String) any());
//    }
//
//
//    @Test
//    void testGetMedicineByshopEmail() throws UnsupportedEncodingException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        medicine.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//        Optional<Medicine> ofResult = Optional.of(medicine);
//        when(medicineRepository.findByEmail((String) any())).thenReturn(ofResult);
//        Optional<Medicine> actualMedicineByshopEmail = medicineServiceImpl.getMedicineByshopEmail("jane.doe@example.org");
//        assertSame(ofResult, actualMedicineByshopEmail);
//        assertTrue(actualMedicineByshopEmail.isPresent());
//        verify(medicineRepository).findByEmail((String) any());
//    }
//
//
//    @Test
//    void testGetMedicineByshopName() throws UnsupportedEncodingException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        medicine.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//        Optional<Medicine> ofResult = Optional.of(medicine);
//        when(medicineRepository.findMedicinesByShopName((String) any())).thenReturn(ofResult);
//        Optional<Medicine> actualMedicineByshopName = medicineServiceImpl.getMedicineByshopName("Shop Name");
//        assertSame(ofResult, actualMedicineByshopName);
//        assertTrue(actualMedicineByshopName.isPresent());
//        verify(medicineRepository).findMedicinesByShopName((String) any());
//    }
//
//
//
//
//
//    @Test
//    void testUpdateDetails3() throws UnsupportedEncodingException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        medicine.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//        when(medicineRepository.existsById((String) any())).thenReturn(true);
//        when(medicineRepository.save((Medicine) any())).thenReturn(medicine);
//        doNothing().when(medicineRepository).deleteById((String) any());
//        Medicine medicine1 = mock(Medicine.class);
//        doNothing().when(medicine1).setDiscountPercentage(anyDouble());
//        doNothing().when(medicine1).setExpireydate((String) any());
//        doNothing().when(medicine1).setImage((Binary) any());
//        doNothing().when(medicine1).setManafacturedate((String) any());
//        doNothing().when(medicine1).setMedicineCatagory((String) any());
//        doNothing().when(medicine1).setMedicineDescription((String) any());
//        doNothing().when(medicine1).setMedicineDiscountprice(anyDouble());
//        doNothing().when(medicine1).setMedicineId((String) any());
//        doNothing().when(medicine1).setMedicineName((String) any());
//        doNothing().when(medicine1).setMedicinePrice(anyDouble());
//        doNothing().when(medicine1).setMedicineUnits(anyInt());
//        doNothing().when(medicine1).setShopEmail((String) any());
//        doNothing().when(medicine1).setShopName((String) any());
//        medicine1.setDiscountPercentage(10.0d);
//        medicine1.setExpireydate("2020-03-01");
//        medicine1.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine1.setManafacturedate("2020-03-01");
//        medicine1.setMedicineCatagory("Medicine Catagory");
//        medicine1.setMedicineDescription("Medicine Description");
//        medicine1.setMedicineDiscountprice(10.0d);
//        medicine1.setMedicineId("42");
//        medicine1.setMedicineName("Medicine Name");
//        medicine1.setMedicinePrice(10.0d);
//        medicine1.setMedicineUnits(1);
//        medicine1.setShopEmail("jane.doe@example.org");
//        medicine1.setShopName("Shop Name");
//        medicineServiceImpl.updateDetails("42", medicine1);
//        verify(medicineRepository).existsById((String) any());
//        verify(medicineRepository).save((Medicine) any());
//        verify(medicineRepository).deleteById((String) any());
//        verify(medicine1).setDiscountPercentage(anyDouble());
//        verify(medicine1).setExpireydate((String) any());
//        verify(medicine1).setImage((Binary) any());
//        verify(medicine1).setManafacturedate((String) any());
//        verify(medicine1).setMedicineCatagory((String) any());
//        verify(medicine1).setMedicineDescription((String) any());
//        verify(medicine1).setMedicineDiscountprice(anyDouble());
//        verify(medicine1, atLeast(1)).setMedicineId((String) any());
//        verify(medicine1).setMedicineName((String) any());
//        verify(medicine1).setMedicinePrice(anyDouble());
//        verify(medicine1).setMedicineUnits(anyInt());
//        verify(medicine1).setShopEmail((String) any());
//        verify(medicine1).setShopName((String) any());
//    }
//
//
//    @Test
//    void testAddMedicine() throws UnsupportedEncodingException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        medicine.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//        when(medicineRepository.save((Medicine) any())).thenReturn(medicine);
//        when(sequenceGeneartor.getSequenceNumber((String) any())).thenReturn("42");
//
//        Medicine medicine1 = new Medicine();
//        medicine1.setDiscountPercentage(10.0d);
//        medicine1.setExpireydate("2020-03-01");
//        medicine1.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine1.setManafacturedate("2020-03-01");
//        medicine1.setMedicineCatagory("Medicine Catagory");
//        medicine1.setMedicineDescription("Medicine Description");
//        medicine1.setMedicineDiscountprice(10.0d);
//        medicine1.setMedicineId("42");
//        medicine1.setMedicineName("Medicine Name");
//        medicine1.setMedicinePrice(10.0d);
//        medicine1.setMedicineUnits(1);
//        medicine1.setShopEmail("jane.doe@example.org");
//        medicine1.setShopName("Shop Name");
//        assertSame(medicine, medicineServiceImpl.addMedicine(medicine1));
//        verify(medicineRepository).save((Medicine) any());
//        verify(sequenceGeneartor).getSequenceNumber((String) any());
//        assertEquals(0.0d, medicine1.getDiscountPercentage());
//        assertEquals("42", medicine1.getMedicineId());
//    }
//
//
//    @Test
//    void testAddMedicineImageBymedicineId() throws IOException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        Binary binary = new Binary("AAAAAAAA".getBytes("UTF-8"));
//        medicine.setImage(binary);
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//
//        Medicine medicine1 = new Medicine();
//        medicine1.setDiscountPercentage(10.0d);
//        medicine1.setExpireydate("2020-03-01");
//        medicine1.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine1.setManafacturedate("2020-03-01");
//        medicine1.setMedicineCatagory("Medicine Catagory");
//        medicine1.setMedicineDescription("Medicine Description");
//        medicine1.setMedicineDiscountprice(10.0d);
//        medicine1.setMedicineId("42");
//        medicine1.setMedicineName("Medicine Name");
//        medicine1.setMedicinePrice(10.0d);
//        medicine1.setMedicineUnits(1);
//        medicine1.setShopEmail("jane.doe@example.org");
//        medicine1.setShopName("Shop Name");
//        when(medicineRepository.save((Medicine) any())).thenReturn(medicine1);
//        when(medicineRepository.findBymedicineId((String) any())).thenReturn(medicine);
//        when(medicineRepository.existsBymedicineId((String) any())).thenReturn(true);
//        assertSame(medicine, medicineServiceImpl.addMedicineImageBymedicineId("42",
//                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")))));
//        Binary image = ((Medicine) medicineServiceImpl.addMedicineImageBymedicineId("42",
//                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))))).getImage();
//        assertEquals(binary, image);
//        assertEquals(8, image.length());
//        assertEquals((byte) 0, image.getType());
//        verify(medicineRepository).findBymedicineId((String) any());
//        verify(medicineRepository).existsBymedicineId((String) any());
//        verify(medicineRepository).save((Medicine) any());
//    }
//
//
//    @Test
//    void testDeleteMedicines() throws UnsupportedEncodingException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        medicine.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//        when(medicineRepository.findBymedicineId((String) any())).thenReturn(medicine);
//        doNothing().when(medicineRepository).deleteById((String) any());
//        when(medicineRepository.existsById((String) any())).thenReturn(true);
//        assertSame(medicine, medicineServiceImpl.deleteMedicines("42"));
//        verify(medicineRepository).existsById((String) any());
//        verify(medicineRepository).findBymedicineId((String) any());
//        verify(medicineRepository).deleteById((String) any());
//    }
//
//
//    @Test
//    void testGetMedicineBymedicineName() throws UnsupportedEncodingException {
//        Medicine medicine = new Medicine();
//        medicine.setDiscountPercentage(10.0d);
//        medicine.setExpireydate("2020-03-01");
//        medicine.setImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        medicine.setManafacturedate("2020-03-01");
//        medicine.setMedicineCatagory("Medicine Catagory");
//        medicine.setMedicineDescription("Medicine Description");
//        medicine.setMedicineDiscountprice(10.0d);
//        medicine.setMedicineId("42");
//        medicine.setMedicineName("Medicine Name");
//        medicine.setMedicinePrice(10.0d);
//        medicine.setMedicineUnits(1);
//        medicine.setShopEmail("jane.doe@example.org");
//        medicine.setShopName("Shop Name");
//        Optional<Medicine> ofResult = Optional.of(medicine);
//        when(medicineRepository.findBymedicineName((String) any())).thenReturn(ofResult);
//        Optional<Medicine> actualMedicineBymedicineName = medicineServiceImpl.getMedicineBymedicineName("Medicine Name");
//        assertSame(ofResult, actualMedicineBymedicineName);
//        assertTrue(actualMedicineBymedicineName.isPresent());
//        verify(medicineRepository).findBymedicineName((String) any());
//    }
//
//
//    @Test
//    void testGetMedicineBymedicineCatagory() {
//        ArrayList<Medicine> medicineList = new ArrayList<>();
//        when(medicineRepository.findBymedicineCatagory((String) any())).thenReturn(medicineList);
//        Object actualMedicineBymedicineCatagory = medicineServiceImpl.getMedicineBymedicineCatagory("Medicine Catagory");
//        assertSame(medicineList, actualMedicineBymedicineCatagory);
//        assertTrue(((Collection<Object>) actualMedicineBymedicineCatagory).isEmpty());
//        verify(medicineRepository).findBymedicineCatagory((String) any());
//    }
//
//
//    @Test
//    void testGetMedicinesByCatagory() {
//        ArrayList<Medicine> medicineList = new ArrayList<>();
//        when(medicineRepository.getBymedicineName((String) any())).thenReturn(medicineList);
//        List<Medicine> actualMedicinesByCatagory = medicineServiceImpl.getMedicinesByCatagory("Medicine Name");
//        assertSame(medicineList, actualMedicinesByCatagory);
//        assertTrue(actualMedicinesByCatagory.isEmpty());
//        verify(medicineRepository).getBymedicineName((String) any());
//    }
//
//
//    @Test
//    void testGetwithMaxDiscount2() {
//        when(medicineRepository.findAll((Sort) any())).thenThrow(new RuntimeException("An error occurred"));
//        assertThrows(RuntimeException.class, () -> medicineServiceImpl.getwithMaxDiscount());
//        verify(medicineRepository).findAll((Sort) any());
//    }
//
//
//}
//
