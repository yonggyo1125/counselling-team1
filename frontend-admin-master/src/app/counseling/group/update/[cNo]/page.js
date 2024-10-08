import AdminOnlyContainer from '@/member/containers/AdminOnlyContainer';
import GroupUpdateContainer from '@/counseling/group/components/GroupUpdateContainer';

const GroupUpdatePage = ({ params }) => {
  return (
    <AdminOnlyContainer>
      <GroupUpdateContainer params={params} />
    </AdminOnlyContainer>
  );
};

export default GroupUpdatePage;
