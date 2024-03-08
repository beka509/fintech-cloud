package kz.fintech.models.refs;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int id;
    private Integer parentMenuId;
    private final List<Menu> subMenus = new ArrayList<>();
    private LocalizedString text;
    private String type;
    private String url;
    private String icon;
    @Builder.Default
    private List<String> privileges = new ArrayList<>();
    private String sortOrder;
    private String position;
    private boolean isArc;
}
