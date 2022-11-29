import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {
    private Bun bun;
    private String nameBun = "NameBun";
    private float priceBun = 100.00f;

    // Создаем булку с именем и стоимостью
    @Before
    public void createBun() {
        bun = new Bun(nameBun, priceBun);
    }

    // Проверка метода названия Булочки
    @Test
    public void equalGetNameBunTest() throws Exception {
        String expected = "NameBun";
        String actual = bun.getName();
        Assert.assertEquals(expected, actual);
    }

    // Проверка метода назначенной цены Булочки
    @Test
    public void equalGetPriceBunTest() throws Exception {
        float expected = 100.00f;
        float actual = bun.getPrice();
        float delta = 0.00f;
        Assert.assertEquals(expected, actual, delta);
    }
}
