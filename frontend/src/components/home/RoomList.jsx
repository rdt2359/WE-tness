import { useSelector, useDispatch } from 'react-redux';
import styled from 'styled-components';
import { useEffect } from 'react';
import { fetchRoomList } from '../../features/room/RoomSlice';
import RoomCard from '../common/RoomCard';

const List = styled.div`
  display: flex;
  flex-wrap: wrap;
`;

export default function RoomList() {
  const rooms = useSelector(state => state.room.rooms);
  const showPrivate = useSelector(state => state.room.showPrivate);
  const workout = useSelector(state => state.room.workout);
  const isRoomsLoaded = useSelector(state => state.room.isRoomsLoaded);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchRoomList());
  }, [dispatch]);

  return (
    <div>
      {isRoomsLoaded ? (
        // 룸 리스트가 로드된 경우 => 룸 리스트 개수에 따라서 처리
        rooms.length === 0 ? (
          <NoRoom content={'진행중인 방이 없습니다.'} />
        ) : (
          <List>
            {rooms.map((room, i) =>
              (room.scope === 'public' || showPrivate) && (workout === '전체' ? true : workout === room.workout) ? (
                <RoomCard key={i} room={room} />
              ) : null
            )}
          </List>
        )
      ) : (
        <NoRoom content={'로그인 후 이용해주세요.'} />
      )}
    </div>
  );
}

const NoRoomBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px;
  font-size: large;
`;

function NoRoom({ content }) {
  return <NoRoomBox>{content}</NoRoomBox>;
}
