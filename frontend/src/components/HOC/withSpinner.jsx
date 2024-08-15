import { Component, useState } from 'react';
import Spinner from '@salesforce/design-system-react/components/spinner';

/**
 * Takes a component and adds functionality to show custom alerts
 * @param WrappedComponent Component to be wrapped with this alert functionality
 * @return Another component with the exact same functionality plus alerts
 */
function withSpinner(WrappedComponent) {
  return class WithSpinner extends Component {
    state = {
      loading: false
    };

    setLoading = loading => {
      this.setState({ loading });
    };

    render = () => (
      <div style={{ position: 'relative' }}>
        {this.state.loading && <Spinner variant="brand" />}
        <WrappedComponent setLoading={this.setLoading} {...this.props} />
      </div>
    );
  };
}

function useSpinner() {
  const [loading, setLoading] = useState(false);

  return [loading ? <Spinner variant="brand" /> : null, setLoading];
}

export { withSpinner, useSpinner };
