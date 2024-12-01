package org.elyashevich.ecommerceapplication.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Router {

    private String path;
    private RouterType type;
}


