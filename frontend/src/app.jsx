import { useState, createContext } from 'react';

import BrandBand from '@salesforce/design-system-react/components/brand-band';
import NavigationBar from './components/navigation/navigationBar';
import NavigationContent from './components/navigation/navigationContent';
import NavigationDrawer from './components/navigation/navigationDrawer';

import { makeStyles, useTheme } from '@material-ui/core/styles';
import useMediaQuery from '@material-ui/core/useMediaQuery';
import Box from '@material-ui/core/Box';

import clsnms from 'classnames';

const useStyles = makeStyles({
  preventScroll: {
    overflowY: 'hidden'
  }
});

export const MobileContext = createContext(false);

export default function App() {
  const [activeContent, setActiveContent] = useState('overview');
  const [navigationOpen, setNavigationOpen] = useState(false);
  const classes = useStyles();
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.only('xs'));

  return (
    <MobileContext.Provider value={isMobile}>
      <NavigationBar
        navigationOpen={navigationOpen}
        activeContent={activeContent}
        onActiveTabChange={setActiveContent}
        onOpenNavigation={setNavigationOpen}
      />

      <NavigationDrawer
        visible={navigationOpen}
        activeContent={activeContent}
        onActiveTabChange={setActiveContent}
        onClose={setNavigationOpen}
      />

      <Box position="relative" className={clsnms({ [classes.preventScroll]: navigationOpen })}>
        <BrandBand theme="lightning-blue">
          <NavigationContent activeContent={activeContent} />
        </BrandBand>
      </Box>
    </MobileContext.Provider>
  );
}
