import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    public String ingredientType;

    public IngredientTypeTest(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    @Parameterized.Parameters
    public static Object[] getParamData() {
        return new Object[][]
                {// передали тестовые данные
                        {"FILLING"},
                        {"SAUCE"},
                };
    }

    // Проверка FILLING и SAUCE в enum IngredientType
    @Test
    public void testIngredientType() {
        String expected = ingredientType;
        Assert.assertEquals(expected, IngredientType.valueOf(ingredientType).toString());
    }
}
