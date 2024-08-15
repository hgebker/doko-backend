import { StrictMode } from 'react';
import ReactDOM from 'react-dom';
import App from './app';
import IconSettings from '@salesforce/design-system-react/components/icon-settings';
import { ThemeProvider } from '@material-ui/core/styles';

ReactDOM.render(
  <StrictMode>
    <IconSettings iconPath="/assets/icons">
      <ThemeProvider>
        <App />
      </ThemeProvider>
    </IconSettings>
  </StrictMode>,
  document.getElementById('root')
);
