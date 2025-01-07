package com.ecommerce.payload;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
     List<ProductDto> productContent;

     private Integer pageNumber;
     private Integer pageSize;
     private Long totalElements;
     private Integer totalPages;
     private boolean lastPage;
}
