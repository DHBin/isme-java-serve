package cn.dhbin.isme.common.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import lombok.Data;

/**
 * 包装分页数据
 */
@Data
public class Page<T> {

    private List<T> pageData;

    private Long total;


    /**
     * mpPage转成Page
     *
     * @param mpPage mp的分页结果
     * @param <T>    类型
     * @return page
     */
    public static <T> Page<T> convert(IPage<T> mpPage) {
        Page<T> page = new Page<>();
        page.setPageData(mpPage.getRecords());
        page.setTotal(mpPage.getTotal());
        return page;
    }
}
