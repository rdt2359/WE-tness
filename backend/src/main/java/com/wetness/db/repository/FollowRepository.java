package com.wetness.db.repository;

import com.wetness.db.entity.Follow;
import com.wetness.db.entity.composite.FollowId;
import com.wetness.model.dto.response.FollowDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    @Query("select f from Follow f where f.follower.id = :followerId and f.following.id = :followingId")
    Follow findByFollowerIdAndFollowingId(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

    @Query("select u.nickname as nickname, (u.refreshToken is not null) as loginState from Follow f join User u on f.following.id = u.id where f.follower.id = :followerId")
    ArrayList<FollowDto> findFollowingDataByFollowerId(@Param("followerId") Long followerId);

    @Query("select u.nickname as nickname, (u.refreshToken is not null) as loginState from Follow f join User u on f.follower.id = u.id where f.following.id = :followingId")
    ArrayList<FollowDto> findFollowerDataByFollowerId(@Param("followingId") Long followingId);
}
