package tests;

import hooks.MyHooks;
import org.testng.annotations.Test;

public class General_Store extends MyHooks {

    @Test
    public void fill_up_initial_info() {
        System.out.println("Test 1");
    }

    @Test
    public void get_error_message() {
        System.out.println("Test 2");
    }

    @Test
    public void find_and_add_product_on_the_cart() {
        System.out.println("Test 3");
    }

    @Test
    public void validate_mobile_gesture() {
        System.out.println("Test 4");
    }

    @Test
    public void hybrid_app_context() {
        System.out.println("Test 5");
    }
}
