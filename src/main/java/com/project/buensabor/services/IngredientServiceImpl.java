package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.IngredientMapper;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Product;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.IngredientRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Service
@Slf4j
public class IngredientServiceImpl extends BaseServicesDTOImpl<Ingredient, IngredientDto, IngredientMapper, Long> implements IngredientService {


    public IngredientServiceImpl(BaseRepository<Ingredient, Long> baseRepository, IngredientMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public boolean validarStock(Long idIngredient, Long cantRequerida) throws CustomException {
        try{
            Optional<Ingredient> ingredientOptional = ingredientRepository.findById(idIngredient);
            if (!ingredientOptional.isPresent()){
                new CustomException("No se encuentra el ingrediente");
            }
            Ingredient ingredient = ingredientOptional.get();

            if (ingredient.getStock()-cantRequerida>0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void descontarStock(Long idIngredient, Long cantADescontar, boolean descontarOreponer) throws CustomException {
        try{
            Optional<Ingredient> ingredientOptional = ingredientRepository.findById(idIngredient);
            if (!ingredientOptional.isPresent()){
                new CustomException("No se encuentra el ingrediente");
            }
            Ingredient ingredient = ingredientOptional.get();
            if(descontarOreponer==false){
                ingredient.setStock(ingredient.getStock()-cantADescontar);
            } else {
                ingredient.setStock(ingredient.getStock()+cantADescontar);
            }
            ingredient = ingredientRepository.save(ingredient);
            //System.out.println("se desconto "+ cantADescontar+" del ingrediente "+ ingredient.getName());

        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<IngredientDto> getIngOrderStockMin() throws CustomException {
        try{
            List<Ingredient> ingredientList = ingredientRepository.getIngredientsOrderStockMin();
            List<IngredientDto> ingredientDtoList = new ArrayList<>();
            for (Ingredient ingredient: ingredientList) {
                ingredientDtoList.add(mapper.convertToDto(ingredient));
            }
            return ingredientDtoList;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Transactional
    public String excelDownload(ArrayList<IngredientDto> ingredientsDto) throws Exception {
        try {
            //Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("Product Ranking");
            Map<String, Object[]> data = new TreeMap<String, Object[]>();
            data.put("1", new Object[]{"PRODUCT NAME", "QUANTITY SOLD"});

            data.put("2", new Object[]{1, "Amit", "Shukla"});
            data.put("3", new Object[]{2, "Lokesh", "Gupta"});
            data.put("4", new Object[]{3, "John", "Adwards"});
            data.put("5", new Object[]{4, "Brian", "Schultz"});
            //Iterate over data and write to sheet
            Set<String> keyset = data.keySet();

            int rownum = 0;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }
            //Write the workbook in file system
            try {
                FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
                workbook.write(out);
                out.close();
                log.info("howtodoinjava_demo.xlsx written successfully on disk.");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

}
