package testdouble;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ChefTestsStubFridge {
    // stub 模拟 happy path
    @Test
    public void should_store_veg_to_fridge_if_fridge_is_not_full_using_stub() throws FridgeFullException {
        Fridge stubFridge = mock(Fridge.class);
        doNothing().when(stubFridge).put("cabbage", "A big cabbage.");
        Chef chef = new Chef(stubFridge);

        boolean isStored = chef.storeVeg("cabbage", "A big cabbage.");

        assertTrue(isStored);
    }

    // stub 模拟 sad path
    @Test
    public void should_not_store_veg_to_fridge_if_fridge_is_full_using_stub() throws FridgeFullException {
        Fridge fridge = mock(Fridge.class);
        doThrow(new FridgeFullException()).when(fridge).put("cabbage", "A big cabbage.");
        Chef chef = new Chef(fridge);

        boolean isStored = chef.storeVeg("cabbage", "A big cabbage.");

        assertFalse(isStored);
    }
}
