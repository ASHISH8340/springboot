//package com.stackroute.shoplistService.ServiceImpl;
//
//
//import com.stackroute.shoplistService.Exception.shopIdNotFound;
//import com.stackroute.shoplistService.Repo.shopRepository;
//import com.stackroute.shoplistService.model.Shop;
//import com.stackroute.shoplistService.service.shopService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class ShopserviceImplTest {
//
//    @Autowired
//    shopService shopService;
//
//    @MockBean
//    private shopRepository repo;
//
//
//    @Test
//    public void addShop(){
//        assertThrows(NullPointerException.class,()-> {
//            Shop shop = new Shop();
//            shop.setShopId("33");
//            shop.setContactEmail("ds@gmail.com");
//            shop.setShopImage(null);
//            when(repo.save(shop)).thenReturn(shop);
//            Shop shopresult = (Shop) shopService.addShop(shop, null);
//            assertEquals(shop, shopresult);
//
//        });
//    }
//
//
//    @Test
//    public void testGetShopyId(){
//        Shop shop=new Shop();
//        shop.setShopId("32");
//        when(repo.findByshopId(shop.getShopId())).thenReturn(Optional.of(shop));
//        Shop shopresult= (Shop) shopService.viewShopById(shop.getShopId());
//        assertEquals(shop,shopresult);
//    }
//    @Test
//    public void testFindByPincode(){
//        assertThrows(shopIdNotFound.class,()-> {
//            Shop shop = new Shop();
//            shop.setPincode("400068");
//            // when(repo.findBypincode(shop.getPincode())).thenReturn(List.of(shop));
//            when(repo.findBypincode(shop.getPincode())).thenReturn(List.of(shop));
//
//            List<Shop> shopList = (List<Shop>) shopService.ViewShopByPincode(shop.getPincode());
//            //   Shop shopresult= (Shop) shopService.ViewShopByPincode("3333");
//
//            assertEquals(shop, shopList.get(0));
//        });
//    }
//
//    @Test
//    public void testfindAll(){
//        Shop shop1=new Shop();
//        shop1.setShopId("11");
//        shop1.setPincode("11");
//
//        Shop shop2=new Shop();
//        shop2.setShopId("22");
//        shop2.setPincode("22");
//
//        when(repo.findAll()).thenReturn(List.of(shop1,shop2));
//        List<Shop> shopListResult= (List<Shop>) shopService.viewallShop();
//        assertEquals(shop1,shopListResult.get(0));
//        assertEquals(shop2,shopListResult.get(1));
//    }
//
//    @Test
//    public void testforDeleteShop(){
//        assertThrows(shopIdNotFound.class,()-> {
//            Shop shop = new Shop();
//            shop.setShopId("121");
//            shopService.deleteShop("121");
//            verify(repo,times(1)).deleteById(shop.getShopId());
//        });
//
//    }
//
//    @Test
//    public void testfindByEmailMedicines(){
//        assertThrows(shopIdNotFound.class,()-> {
//        Shop shop=new Shop();
//        shop.setContactEmail("ash@gmail.com");
//        shop.setShopItems(List.of("dolo","paracetamol"));
//        shopService.findByEmailMedicines("ash@gmail.com");
//        verify(repo,times(1)).findByemail(shop.getContactEmail());
//        });
//    }
//
//
//    @Test
//    public void testUpdateShop(){
//        assertThrows(shopIdNotFound.class,()->{
//        Shop shop=new Shop();
//        shop.setShopId("121");
//        shop.setContactEmail("ram@gmail.com");
//        Shop shop1= (Shop) shopService.updateDetails("121",shop);
//        assertEquals(shop,shop1);
//
//    });
//
//    }
//
//
//}
//
//
