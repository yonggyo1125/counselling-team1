'use client';
import React from 'react';
import styled from 'styled-components';
import { useTranslation } from 'react-i18next';
import { getUserStates } from '@/commons/contexts/UserInfoContext';
const SubMenuBox = styled.aside`
  min-height: 650px;
  background: ${({ theme }) => theme.colors.black};
  a {
    display: block;
    background: ${({ theme }) => theme.colors.gray};
    color: ${({ theme }) => theme.colors.black};
    font-size: ${({ theme }) => theme.fontSizes.medium}px;
    padding: 15px 20px;
  }
  a:hover {
    background: ${({ theme }) => theme.colors.black};
    color: ${({ theme }) => theme.colors.white};
  }
  a + a {
    border-top: 1px solid ${({ theme }) => theme.colors.black};
  }
`;

const Submenus = () => {
  const { t } = useTranslation();
  const { isStudent, isProfessor } = getUserStates();

  return (
    <SubMenuBox>
      <a href="/mypage/info">{t('회원정보수정')}</a>
      <a href="/mypage/board">{t('내가 작성한 게시글')}</a>
      {isStudent && <a href='/mypage/test'>{t('온라인 심리검사')}</a>}
      {isProfessor && <a href='/mypage/mystudent'>{t('담당 학생')}</a>}
    </SubMenuBox>
  );
};

export default React.memo(Submenus);
