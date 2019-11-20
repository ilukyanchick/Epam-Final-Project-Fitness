function checkNutrition() {
    var nutritionDescriptionInput = document.getElementById("nutrition_description");
    if (nutritionDescriptionInput.value==="" || (!/[A-Za-z0-9][A-Za-z,.()\s0-9]{1,299}/.test(nutritionDescriptionInput.value))) {
        var nameRegisterTitle = nutritionDescriptionInput.getAttribute("title");
        nutritionDescriptionInput.setCustomValidity(nameRegisterTitle);
    } else {
        nutritionDescriptionInput.setCustomValidity('');
    }
}