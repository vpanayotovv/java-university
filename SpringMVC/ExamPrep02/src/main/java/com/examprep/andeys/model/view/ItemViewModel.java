package com.examprep.andeys.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemViewModel {

    private Long id;
    private String name;
    private String description;
    private String price;
    private String imgUrl;

}
