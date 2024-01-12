package com.insignia.constants;

public class ProductValidatorConstants {

	public static final String validProductIdMessage = "Please enter product id";
	public static final String validProductIdErrorCode = "621";
	public static final String validProductIdLengthMessage = "Length of product id length upto 30 characters only";
	public static final String validProductIdLengthErrorCode = "622";

	public static final String validProductNameMessage = "Please enter productName";
	public static final String validProductNameErrorCode = "601";
	public static final String validProductNameLengthMessage = "Length of product name length upto 100 characters only";
	public static final String validProductNameLengthErrorCode = "602";

	public static final String validMeasuringQuantityLength = "Please enter valid measuring quantity length  ";
	public static final String validMeasuringQuantityLengthErrorCode = "604";

	public static final String validDescriptionLength = "Length of product description length upto 255 characters only";
	public static final String validDescriptionLengthErrorCode = "606";

	public static final String validMeasuringUnit = "Please enter measuringunit";
	public static final String validMeasuringUnitErrorCode = "607";
	public static final String validMeasuringUnitLength = "Please enter measuringunit length upto 64 characters only";
	public static final String validMeasuringUnitLengthErrorCode = "608";

	public static final String regularExpression = "^[A-Za-z0-9_\\-.@]+$";

	public static final String validateProductDetailsMessage = "Details already existing in the system";
	public static final String validateProductDetailsErrorCode = "611";

	public static final String subcategoryIdIsNotFoundMessage = "Provided subcategory id is invalid";
	public static final String subcategoryIdIsNotFoundErrorCode = "612";

	public static final String productDetailsIsNotFoundMessage = "Product Details not exist in the system";
	public static final String productDetailsIsNotFoundErrorCode = "613";

	public static final String validateProductPerUnitActualPriceLengthMessage = "Please enter product per unit actual price length upto 13 characters only";
	public static final String validateProductPerUnitActualPriceLengthErrorCode = "615";

	public static final String validateProductPerUnitCurrentPriceLengthMessage = "Please enter product per unit current price length upto 13 characters only";
	public static final String validateProductPerUnitCurrentPriceLengthErrorCode = "617";

	public static final String validateProductLengthMessage = "Please enter product length upto 7 characters only";
	public static final String validateProductLengthErrorCode = "616";

	public static final String validateHeightLengthMessage = "Please enter height length upto 7 characters only";
	public static final String validateHeightLengthErrorCode = "617";

	public static final String validateWidthLengthMessage = "Please enter width length upto 7 characters only";
	public static final String validateWidthLengthErrorCode = "618";

	public static final String validateDimensionUnitLengthMessage = "Please enter dimension unit length upto 10 characters only";
	public static final String validateDimensionUnitLengthErrorCode = "619";

	public static final String validateProductIdNotUpdatedMessage = "Product id can't be updated";
	public static final String validateProductIdNotUpdatedErrorCode = "620";
	
	public static final String validateMaterialsLengthMessage = "Please enter materials length upto 128 characters only";
	public static final String validateMaterialsLengthErrorCode = "623";
	
	public static final String validateColoursLengthMessage = "Please enter colours length upto 128 characters only";
	public static final String validateColoursLengthErrorCode = "624";
	public static final String filter_colour = "Colour";
	public static final String filter_material = "Material";
	public static final String filter_subcategory = "SubCategory";
	public static final String filter_min_price = "MinPrice";
	public static final String filter_max_price = "MaxPrice";
	public static final String filter_sorting_criteria = "SortingCriteria";
	public static final String filter_availability= "Availability";
}
