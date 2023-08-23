import Modal from 'react-modal';
import { useState } from 'react';
import './Detail.css';

export default function CartModal (props)  {
    return(
        <Modal className='ModalPortal'>
            <div>
                <span>장바구니에 추가되었습니다.</span>
                <button onClick={() => props.hideModal}>확인</button>
            </div>
        </Modal>
    )
};