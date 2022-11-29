import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import java.util.List;

@RunWith(value = Parameterized.class)
public class BurgerTest {
    Burger burger;
    // Считаем список доступных булок из базы данных
    List<Bun> bunsListTest;
    // Считаем список доступных ингредиентов из базы данных
    List<Ingredient> ingredientsListTest;
    private Bun bun;
    private Ingredient ingredient, ingredient2;
    private String bunPrint;
    private String ingredientPrint, ingredientPrint2;
    private String totalPriceBun;
    private String totalPriceIngredient;
    private String totalPriceTwoIngredient;


    public BurgerTest(Bun bun, Ingredient ingredient, Ingredient ingredient2, String bunPrint,
                            String ingredientPrint, String ingredientPrint2, String totalPriceBun,
                                String totalPriceIngredient, String totalPriceTwoIngredient) {
        this.bun = bun;
        this.ingredient = ingredient;
        this.ingredient2 = ingredient2;
        this.bunPrint = bunPrint;
        this.ingredientPrint = ingredientPrint;
        this.ingredientPrint2 = ingredientPrint2;
        this.totalPriceBun = totalPriceBun;
        this.totalPriceIngredient = totalPriceIngredient;
        this.totalPriceTwoIngredient = totalPriceTwoIngredient;
    }

    @Mock
    Database database;
    @Mock
    List<Bun> bunsList;
    @Mock
    List<Ingredient> ingredientsList;

    @Parameterized.Parameters
    public static Object[] getSumData()
    {
        return new Object[][]
                {// передали тестовые данные
                        {new Bun("black bun", 100),
                                new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                                "(==== black bun ====)\r\n",
                                "= sauce hot sauce =\r\n",
                                "= filling cutlet =\r\n",
                                "Price: 200,000000\r\n",
                                "Price: 300,000000\r\n",
                                "Price: 400,000000\r\n"},

                        {new Bun("white bun", 200),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                                new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                                "(==== white bun ====)\r\n",
                                "= filling cutlet =\r\n",
                                "= sauce hot sauce =\r\n",
                                "Price: 400,000000\r\n",
                                "Price: 500,000000\r\n",
                                "Price: 600,000000\r\n"},

                        {new Bun("red bun", 300),
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200),
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 300),
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
        database = new Database();
        bunsListTest = database.availableBuns();
        ingredientsListTest = database.availableIngredients();
    }

    // Проверка метода сборки бургера
    @Test
    public void equalBunBurgerTest() throws Exception {
        Mockito.when(bunsList.get(0)).thenReturn(bun);
        burger.setBuns(bunsList.get(0));
        String expected = bunPrint + bunPrint + "\r\n" + totalPriceBun;

//        System.out.println(expected);
//        System.out.println(burger.getReceipt());

        String actual = burger.getReceipt();
        Assert.assertEquals(expected, actual);
    }

    // Проверка метода добавления ингредиента
    @Test
    public void equalIngredientBurgerTest() throws Exception {
        Mockito.when(bunsList.get(0)).thenReturn(bun);
        burger.setBuns(bunsList.get(0));

        Mockito.when(ingredientsList.get(0)).thenReturn(ingredient);
        burger.addIngredient(ingredientsList.get(0));

        String expected = bunPrint + ingredientPrint + bunPrint + "\r\n" + totalPriceIngredient;

//        System.out.println(expected);
//        System.out.println(burger.getReceipt());

        String actual = burger.getReceipt();
        Assert.assertEquals(expected, actual);
    }

    // Проверка метода перемещения слоя с ингредиентом
    @Test
    public void equalMoveIngredientTest() throws Exception {
        Mockito.when(bunsList.get(0)).thenReturn(bun);
        burger.setBuns(bunsList.get(0));

        Mockito.when(ingredientsList.get(0)).thenReturn(ingredient);
        burger.addIngredient(ingredientsList.get(0));

        Mockito.when(ingredientsList.get(0)).thenReturn(ingredient2);
        burger.addIngredient(ingredientsList.get(0));

        // Переместим слой с ингредиентом
        burger.moveIngredient(0, 1);

        String expected = bunPrint + ingredientPrint2 + ingredientPrint + bunPrint + "\r\n" + totalPriceTwoIngredient;

//        System.out.println(expected);
//        System.out.println(burger.getReceipt());

        String actual = burger.getReceipt();
        Assert.assertEquals(expected, actual);
    }

    // Проверка метода удаления ингредиентов
    @Test
    public void equalRemoveIngredientTest() throws Exception {
        Mockito.when(bunsList.get(0)).thenReturn(bun);
        burger.setBuns(bunsList.get(0));

        Mockito.when(ingredientsList.get(0)).thenReturn(ingredient);
        burger.addIngredient(ingredientsList.get(0));

        // Удалим ингредиент
        burger.removeIngredient(0);

        String expected = bunPrint + bunPrint + "\r\n" + totalPriceBun;

//        System.out.println(expected);
//        System.out.println(burger.getReceipt());

        String actual = burger.getReceipt();
        Assert.assertEquals(expected, actual);
    }
}
