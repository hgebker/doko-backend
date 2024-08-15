import VerticalNavigation from '@salesforce/design-system-react/components/vertical-navigation';
import { makeStyles } from '@material-ui/core/styles';
import { NAV_CATEGORIES } from '../../constants/navigation';
import Drawer from '@material-ui/core/Drawer';

const useStyles = makeStyles({
  drawer: {
    width: '100%'
  }
});

const NavigationDrawer = ({ activeContent, visible, onActiveTabChange, onClose }) => {
  const classes = useStyles();

  const handleSelect = (_, { item }) => {
    onActiveTabChange(item.id);
    onClose(false);
  };

  return (
    <Drawer anchor="left" open={visible} disablePortal classes={{ paper: classes.drawer }}>
      <VerticalNavigation
        categories={NAV_CATEGORIES}
        selectedId={activeContent}
        onSelect={handleSelect}
        className="slds-theme_default"
      />
    </Drawer>
  );
};

export default NavigationDrawer;
