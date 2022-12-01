import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.*;

@RunWith(value = Parameterized.class)
public class BurgerTest {
    Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredient2;

    private String bunName;
    private float bunPrice;

    private IngredientType ingType, ingType2;
    private String ingName, ingName2;
    private float ingPrice, ingPrice2;

    private String bunPrint;

    private String ingPrint, ingPrint2;
    private String totalPriceBun;
    private String totalPriceIngredient;
    private String totalPriceTwoIngredient;

    public BurgerTest(String bunName, float bunPrice,
                      IngredientType ingType, String ingName, float ingPrice,
                      IngredientType ingType2, String ingName2, float ingPrice2,
                      String bunPrint, String ingPrint, String ingPrint2, String totalPriceBun,
                      String totalPriceIngredient, String totalPriceTwoIngredient) {

        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingType = ingType;
        this.ingName = ingName;
        this.ingPrice = ingPrice;
        this.ingType2 = ingType2;
        this.ingName2 = ingName2;
        this.ingPrice2 = ingPrice2;
        this.bunPrint = bunPrint;
        this.ingPrint = ingPrint;
        this.ingPrint2 = ingPrint2;
        this.totalPriceBun = totalPriceBun;
        this.totalPriceIngredient = totalPriceIngredient;
        this.totalPriceTwoIngredient = totalPriceTwoIngredient;
    }

    @Parameterized.Parameters
    public static Object[] getParamData() {
        return new Object[][]
                {// передали тестовые данные
                        {"black bun",
                                100f,
                                IngredientType.SAUCE,
                                "hot sauce",
                                100f,
                                IngredientType.FILLING,
                                "cutlet",
                                100f,
                                "(==== black bun ====)\r\n",
                                "= sauce hot sauce =\r\n",
                                "= filling cutlet =\r\n",
                                "Price: 200,000000\r\n",
                                "Price: 300,000000\r\n",
                                "Price: 400,000000\r\n"},

                        {"white bun", 200f,
                                IngredientType.FILLING, "cutlet", 100f,
                                IngredientType.SAUCE, "hot sauce", 100f,
                                "(==== white bun ====)\r\n",
                                "= filling cutlet =\r\n",
                                "= sauce hot sauce =\r\n",
                                "Price: 400,000000\r\n",
                                "Price: 500,000000\r\n",
                                "Price: 600,000000\r\n"},

                        {"red bun", 300f,
                                IngredientType.FILLING, "dinosaur", 200f,
                                IngredientType.SAUCE, "chili sauce", 300f,
                                "(==== red bun ====)\r\n",
                                "= filling dinosaur =\r\n",
                                "= sauce chili sauce =\r\n",
                                "Price: 600,000000\r\n",
                                "Price: 800,000000\r\n",
                                "Price: 1100,000000\r\n"}
                };
    }

    // Подготовка Бургера
    @Before
    public void createBurger() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    // Проверка метода сборки бургера
    @Test
    public void equalBunBurgerTest() {

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(bun);

        String expected = bunPrint + bunPrint + "\r\n" + totalPriceBun;

        String actual = burger.getReceipt();
        Assert.assertEquals("Ошибка, это не булка" + bunName, expected, actual);
    }

    // Проверка метода добавления ингредиента
    @Test
    public void equalIngredientBurgerTest() throws Exception {

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Mockito.when(ingredient.getType()).thenReturn(ingType);
        Mockito.when(ingredient.getName()).thenReturn(ingName);
        Mockito.when(ingredient.getPrice()).thenReturn(ingPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expected = bunPrint + ingPrint + bunPrint + "\r\n" + totalPriceIngredient;

        String actual = burger.getReceipt();
        Assert.assertEquals("Ошибка, это не бургер из " +
                bunName + " " + ingName + " " + bunName, expected, actual);
    }

    // Проверка метода перемещения слоя с ингредиентом
    @Test
    public void equalMoveIngredientTest() throws Exception {
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Mockito.when(ingredient.getType()).thenReturn(ingType);
        Mockito.when(ingredient.getName()).thenReturn(ingName);
        Mockito.when(ingredient.getPrice()).thenReturn(ingPrice);

        Mockito.when(ingredient2.getType()).thenReturn(ingType2);
        Mockito.when(ingredient2.getName()).thenReturn(ingName2);
        Mockito.when(ingredient2.getPrice()).thenReturn(ingPrice2);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        // Переместим слой с ингредиентом
        burger.moveIngredient(0, 1);

        String expected = bunPrint + ingPrint2 + ingPrint + bunPrint + "\r\n" + totalPriceTwoIngredient;

        System.out.println(expected);
        System.out.println(burger.getReceipt());

        String actual = burger.getReceipt();
        Assert.assertEquals("Ошибка, это не бургер из " +
                bunName + " " + ingName2 + " " + ingName + " " + bunName, expected, actual);
    }

    // Проверка метода удаления ингредиентов
    @Test
    public void equalRemoveIngredientTest() throws Exception {
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Mockito.when(ingredient.getType()).thenReturn(ingType);
        Mockito.when(ingredient.getName()).thenReturn(ingName);
        Mockito.when(ingredient.getPrice()).thenReturn(ingPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        // Удалим ингредиент
        burger.removeIngredient(0);

        String expected = bunPrint + bunPrint + "\r\n" + totalPriceBun;

        System.out.println(expected);
        System.out.println(burger.getReceipt());

        String actual = burger.getReceipt();
        Assert.assertEquals("Ошибка, это не булка" + bunName, expected, actual);
    }
}
