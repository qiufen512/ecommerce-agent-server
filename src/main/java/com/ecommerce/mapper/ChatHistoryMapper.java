package com.ecommerce.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.entity.ChatHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Chat History Mapper
 */
@Mapper
public interface ChatHistoryMapper extends BaseMapper<ChatHistory> {

    /**
     * Query session history records
     *
     * @param sessionId Session ID
     * @param limit     Limit
     * @return History records list
     */
    @Select("SELECT * FROM chat_history WHERE session_id = #{sessionId} ORDER BY created_at ASC LIMIT #{limit}")
    List<ChatHistory> listBySessionId(@Param("sessionId") String sessionId, @Param("limit") int limit);
}