import { Component, useState, createRef } from 'react';
import { createPortal } from 'react-dom';
import Modal from '@salesforce/design-system-react/components/modal';
import Button from '@salesforce/design-system-react/components/button';
import Settings from '@salesforce/design-system-react/components/settings';
import { withStyles, makeStyles } from '@material-ui/core/styles';

Settings.setAppElement('#root');

const styles = {
  modalContent: {
    overflow: 'visible',
    maxHeight: '80vh'
  },
  '@media screen and (max-width: 500px)': {
    modalContent: {
      maxHeight: '50vh'
    }
  }
};

const buildModal = (open, { heading, child, buttons, options }, handleCloseClicked, classes) => {
  const Child = child.type || 'div';
  const ref = createRef();

  return createPortal(
    <Modal
      isOpen={open}
      heading={heading}
      onRequestClose={handleCloseClicked}
      contentClassName={classes.modalContent}
      footer={
        <>
          {buttons.map(button => (
            <Button
              key={button.label}
              label={button.label}
              onClick={() => {
                button.action?.(ref?.current?.state);
                handleCloseClicked();
              }}
              disabled={button.disabled?.()}
              variant={button.variant}
            />
          ))}
        </>
      }
      {...options}>
      <Child ref={ref} {...child.attributes} />
    </Modal>,
    document.getElementById('modal')
  );
};

const DEFAULT_CONFIG = {
  heading: 'Default',
  buttons: [],
  child: {
    type: 'div',
    attributes: {}
  },
  onClose: () => {
    return;
  },
  options: {}
};

/**
 * Takes a component and adds functionality to easily show different modals
 * @param WrappedComponent Component to be wrapped with the modal functionality
 * @return Another component with the exact same functionality plus modal
 */
function withModal(WrappedComponent) {
  class WithModal extends Component {
    state = {
      open: false
    };
    config = DEFAULT_CONFIG;

    openModal = config => {
      this.config = config;
      this.setState({ open: true });
    };

    handleCloseClicked = () => {
      const config = this.config;
      if (config.onClose) {
        config.onClose();
      }
      this.setState({ open: false });
    };

    render = () => (
      <>
        {buildModal(this.state.open, this.config, this.handleCloseClicked, this.props.classes)}
        <WrappedComponent openModal={this.openModal} {...this.props} />
      </>
    );
  }

  return withStyles(styles)(WithModal);
}

function useModal() {
  const [open, setOpen] = useState(false);
  const [config, setConfig] = useState(DEFAULT_CONFIG);
  const classes = makeStyles(styles)();

  const openModal = config => {
    setConfig(config);
    setOpen(true);
  };

  const handleCloseClicked = () => {
    if (config.onClose) {
      config.onClose();
    }
    setOpen(false);
  };

  return [buildModal(open, config, handleCloseClicked, classes), openModal];
}

export { withModal, useModal };
