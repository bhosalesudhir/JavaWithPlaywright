public static class Log {

    public static void Error(String message) {
        Allure.addAttachment("Error", message);
    }
    public static void Info(String message) {
        Allure.addAttachment("Info", message);
    }


}