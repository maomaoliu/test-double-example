package testdouble;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ChefTestsSpyFridge {

    /*
     * 场景1：
     * 除了验证返回结果以外，
     * 厨师希望验证存放成功或失败对冰箱容量的影响
     */

    class FridgeSpy extends Fridge{
        public int remainingAmount() {
            return super.MAX_CAPACITY - super.map.size();
        }
    }

    // spy 通过增加新的观察点进行状态验证
    @Test
    public void remaining_amount_should_reduce_after_put_things_into_fridge_using_spy() {
        FridgeSpy fridgeSpy = new FridgeSpy();
        int remainingAmountBefore = fridgeSpy.remainingAmount();
        Chef chef = new Chef(fridgeSpy);

        chef.storeVeg("cabbage", "A big cabbage.");

        assertEquals(fridgeSpy.remainingAmount(), remainingAmountBefore - 1);
    }

    // spy 通过增加新的观察点进行状态验证
    @Test
    public void remaining_amount_should_not_change_when_put_things_into_fridge_failed_using_spy()
            throws FridgeFullException {
        FridgeSpy realFridgeSpy = new FridgeSpy();
        int remainingAmountBefore = realFridgeSpy.remainingAmount();
        FridgeSpy spyFridgeSpy = spy(realFridgeSpy);
        doThrow(new FridgeFullException()).when(spyFridgeSpy).put("cabbage", "A big cabbage.");
        Chef chef = new Chef(spyFridgeSpy);

        chef.storeVeg("cabbage", "A big cabbage.");

        assertEquals(spyFridgeSpy.remainingAmount(), remainingAmountBefore);
    }


    /*
     * 场景2：
     * 除了验证返回结果以外，
     * 厨师希望验证无论存放成功与否都关了冰箱的门
     */

    private Fridge buildFullFridge() {
        Fridge fridge = new Fridge();
        try {
            fridge.put("cookie", "delicious cookie");
            fridge.put("eggs", "a lot of eggs");
        } catch (FridgeFullException ignored) {
        }
        return fridge;
    }

    // spy 验证 close() 方法 被调用，使用真实方法，创建真实的放满的冰箱
    @Test
    public void should_close_door_if_fridge_is_full_using_spy() {
        Fridge fridge = buildFullFridge();
        Fridge spy = spy(fridge);
        Chef chef = new Chef(spy);

        chef.storeVeg("cabbage", "A big cabbage.");

        // 这里是行为验证
        verify(spy).close();
    }

    // spy 验证 close() 方法 被调用，使用真实方法，stub 冰箱使其不能放入新物品
    @Test
    public void should_close_door_if_fridge_is_full_using_spy_2() throws FridgeFullException {
        Fridge fridge = new Fridge();
        Fridge spy = spy(fridge);
        doThrow(new FridgeFullException()).when(spy).put("cabbage", "A big cabbage.");
        Chef chef = new Chef(spy);

        chef.storeVeg("cabbage", "A big cabbage.");

        verify(spy).close();
    }
}
