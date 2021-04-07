package testdouble;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ChefTestsMockFridge {
    /*
     * 场景：
     * 除了验证返回结果以外，
     * 厨师希望验证无论存放成功与否都关了冰箱的门
     */

    // mock 验证 close() 方法 被调用
    @Test
    public void should_close_door_if_fridge_is_not_full_using_mock() throws FridgeFullException {
        Fridge mockFridge = mock(Fridge.class);
        doNothing().when(mockFridge).put("cabbage", "A big cabbage.");
        Chef chef = new Chef(mockFridge);

        chef.storeVeg("cabbage", "A big cabbage.");

        // 这里是行为验证
        verify(mockFridge).close();
    }

    // mock 验证 close() 方法 被调用
    @Test
    public void should_close_door_if_fridge_is_full_using_mock() throws FridgeFullException {
        Fridge mockFridge = mock(Fridge.class);
        doThrow(new FridgeFullException()).when(mockFridge).put("cabbage", "A big cabbage.");
        Chef chef = new Chef(mockFridge);

        chef.storeVeg("cabbage", "A big cabbage.");

        verify(mockFridge).close();
    }

    // mock VS stub
    @Test
    public void should_verify_exact_method() throws FridgeFullException {
        Fridge mockFridge = mock(Fridge.class);
        // this is stub
        doNothing().when(mockFridge).put("cabbage", "A big cabbage.");
        Chef chef = new Chef(mockFridge);

        chef.storeVeg("cabbage", "A big cabbage.");
        // this is mock
        verify(mockFridge).put("cabbage", "A big cabbage.");
    }
}
