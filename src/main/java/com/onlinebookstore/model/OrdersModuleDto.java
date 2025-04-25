package com.onlinebookstore.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersModuleDto {

    private List<String> title;

    private Long custmerId;
}
