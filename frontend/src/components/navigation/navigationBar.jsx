import { Component } from 'react';
import GlobalNavigationBar from '@salesforce/design-system-react/components/global-navigation-bar';
import GlobalNavigationBarRegion from '@salesforce/design-system-react/components/global-navigation-bar/region';
import GlobalNavigationBarButton from '@salesforce/design-system-react/components/global-navigation-bar/button';
import Button from '@salesforce/design-system-react/components/button';
import withStyles from '@material-ui/styles/withStyles';
import { NAV_ITEMS } from '../../constants/navigation';

const styles = {
  'navigation-bar': {
    position: 'sticky',
    top: 0,
    overflow: 'hidden',
    textTransform: 'none',
    zIndex: 3
  },

  '@media screen and (max-width: 500px)': {
    'navigation-bar_nav-region': {
      display: 'none'
    }
  },

  '@media screen and (min-width: 500px)': {
    'navigation-bar_launcher-region': {
      '& button': {
        display: 'none'
      }
    }
  }
};

class NavigationBar extends Component {
  handleButtonClicked = event => {
    this.props.onActiveTabChange(event.target.id);
  };
  handleOpenNavigation = () => {
    this.props.onOpenNavigation(!this.props.navigationOpen);
  };

  render = () => (
    <GlobalNavigationBar className={this.props.classes['navigation-bar']}>
      <GlobalNavigationBarRegion className={this.props.classes['navigation-bar_launcher-region']} region="primary">
        <Button
          variant="icon"
          iconCategory="utility"
          iconName="rows"
          iconSize="large"
          onClick={this.handleOpenNavigation}
        />
        <h1 className="slds-text-heading_medium slds-align-content-center slds-var-p-horizontal_small">
          Doko Kartclub Münster e.V
        </h1>
      </GlobalNavigationBarRegion>

      <GlobalNavigationBarRegion
        className={this.props.classes['navigation-bar_nav-region']}
        region="secondary"
        navigation>
        {NAV_ITEMS.map(({ id, label }) => (
          <GlobalNavigationBarButton
            key={id}
            id={id}
            label={label}
            active={this.props.activeContent === id}
            onClick={this.handleButtonClicked}
          />
        ))}
      </GlobalNavigationBarRegion>
    </GlobalNavigationBar>
  );
}

export default withStyles(styles)(NavigationBar);
