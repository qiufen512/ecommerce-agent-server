package com.ecommerce.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.entity.ChatHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 对话历史记录 Mapper
 */
@Mapper
public interface ChatHistoryMapper extends BaseMapper<ChatHistory> {

    /**
     * 查询会话历史记录
     *
     * @param sessionId 会话ID
     * @param limit     限制条数
     * @return 历史记录列表
     */
    @Select("SELECT * FROM chat_history WHERE session_id = #{sessionId} ORDER BY created_at ASC LIMIT #{limit}")
    List<ChatHistory> listBySessionId(@Param("sessionId") String sessionId, @Param("limit") int limit);
}