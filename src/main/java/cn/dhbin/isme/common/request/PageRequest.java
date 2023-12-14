package cn.dhbin.isme.common.request;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest {

    /**
     * 页数
     */
    private Integer pageNo = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 10;


    /**
     * 转换成mp的page
     *
     * @param <T> 类型
     * @return Page
     */
    public <T> IPage<T> toPage() {
        Page<T> page = new Page<>();
        page.setSize(pageSize);
        page.setMaxLimit(500L);
        page.setCurrent(pageNo);
        return page;
    }
}
