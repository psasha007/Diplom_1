import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

public class IngredientTest {
    private Ingredient ingredient;
    private String nameIngredient = "NameIngredient";
    private float priceIngredient = 100.00f;

    private List<IngredientType> ingredientType;

    // Создаем Модель ингредиента
    @Before
    public void createIngredient() {
        // У модели ингредиента есть тип (начинка/FILLING или соус/SAUCE), название и цена.
        ingredient = new Ingredient(IngredientType.SAUCE, nameIngredient, priceIngredient);
    }

    // Проверка метода названия ингредиента
    @Test
    public void equalGetNameIngredientTest() throws Exception {
        String expected = "NameIngredient";
        String actual = ingredient.getName();
        Assert.assertEquals("Неверное название ингредиента",expected, actual);
    }

    // Проверка метода назначенной цены ингредиента
    @Test
    public void equalGetPriceIngredientTest() throws Exception {
        float expected = 100.00f;
        float actual = ingredient.getPrice();
        float delta = 0.00f;
        Assert.assertEquals("Отклонение в стоимости ",expected, actual, delta);
    }

    // Проверка метода типа ингредиента
    @Test
    public void equalGetTypeIngredientTest() throws Exception {
        IngredientType expected = IngredientType.SAUCE;
        IngredientType actual = ingredient.getType();
        Assert.assertEquals("Это не соус ", expected, actual);
    }
}
