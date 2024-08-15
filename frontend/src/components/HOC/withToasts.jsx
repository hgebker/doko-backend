import { Component, useState } from 'react';
import { createPortal } from 'react-dom';
import Toast from '@salesforce/design-system-react/components/toast';
import ToastContainer from '@salesforce/design-system-react/components/toast/container';

const buildToast = ({ open, title, message, variant, handleClose }) => {
  if (!open) {
    return null;
  }

  const labels = {
    heading: [title],
    details: [message]
  };
  return createPortal(
    <ToastContainer>
      <Toast labels={labels} variant={variant || 'error'} duration={5000} onRequestClose={handleClose} />
    </ToastContainer>,
    document.getElementById('toasts')
  );
};

/**
 * Takes a component and adds functionality to show custom alerts
 * @param WrappedComponent Component to be wrapped with this alert functionality
 * @return Another component with the exact same functionality plus alerts
 */
function withToasts(WrappedComponent) {
  return class WithToasts extends Component {
    state = {
      open: false,
      title: '',
      message: '',
      variant: 'error'
    };

    showToast = (title, message, variant) => {
      this.setState({ title, message, variant, open: true });
    };

    handleClose = (_, reason) => {
      if (reason === 'clickaway') {
        return;
      }

      this.setState({ open: false });
    };

    render = () => (
      <>
        {buildToast({ ...this.state, handleClose: this.handleClose })}
        <WrappedComponent showToast={this.showToast} {...this.props} />
      </>
    );
  };
}

function useToasts() {
  const [open, setOpen] = useState(false);
  const [title, setTitle] = useState('');
  const [message, setMessage] = useState('');
  const [variant, setVariant] = useState('error');

  const showToast = (title, message, variant) => {
    setTitle(title);
    setMessage(message);
    setVariant(variant);
    setOpen(true);
  };

  const handleClose = (_, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setOpen(false);
  };

  const toastConfig = { open, title, message, variant, handleClose };
  return [buildToast(toastConfig), showToast];
}

export { withToasts, useToasts };
