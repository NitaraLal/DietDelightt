public class DietValidation {
   private static String[] availableDiets = new String[] {"Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Ovo-Vegetarian", "Vegan", "Pescetarian",
            "Paleo", "Primal", "Low FODMAP", "Whole30"};

   public static boolean validate (String diet) {
      for (String each : availableDiets) {
         if (each.equalsIgnoreCase(diet)) {
            return true;
         }
      }
      return false;
   }

   public static void printDiets () {
      for (String each : availableDiets) {
         System.out.print(each + ", ");
      }
   }

}
