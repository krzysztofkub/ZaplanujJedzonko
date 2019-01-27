--SQL pobierający szczegóły posiłków w określonym planie
SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description
FROM `recipe_plan`
JOIN day_name on day_name.id=day_name_id
JOIN recipe on recipe.id=recipe_id WHERE plan_day_id = 1 -- zamiast 1 należy wstawić id planu do pobrania --
ORDER by day_name.order, recipe_plan.order


-- SQL - pobiera najnowszy plan dla zadanego użytkownika (tabela admins)
SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.id as recipe_id FROM `recipe_plan` JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE plan_id =  (SELECT MAX(id) from plan WHERE admin_id = 1)  ORDER by day_name.order, recipe_plan.order;
-- SQL - last added plan
SELECT * FROM plan  WHERE id = (SELECT MAX(id) FROM plan WHERE admin_id = ?);